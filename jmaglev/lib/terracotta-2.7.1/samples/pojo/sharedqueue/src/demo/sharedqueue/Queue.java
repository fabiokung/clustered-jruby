/**
 *
 * All content copyright (c) 2003-2008 Terracotta, Inc.,
 * except as may otherwise be noted in a separate copyright notice.
 * All rights reserved.
 *
 */
package demo.sharedqueue;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 *  Description of the Class
 *
 *@author    Terracotta, Inc.
 */
public class Queue {
   private List queue = Collections.synchronizedList(new LinkedList());

   private List workers = Collections.synchronizedList(new LinkedList());

   private List completedJobs = Collections.synchronizedList(new LinkedList());

   private int nextJobId;

   private int port;

   private static final int MAX_HISTORY_LENGTH = 15;

   private static final int MAX_QUEUE_LENGTH = 150;

   public Queue(int port) {
      this.port = port;
      this.nextJobId = 1;
   }

   public final Job getJob() {
      synchronized (queue) {
         while (queue.size() == 0) {
            try {
               queue.wait();
            }
            catch (InterruptedException e) {
               throw new RuntimeException(e);
            }
         }
         return (Job) queue.remove(0);
      }
   }

   public final String getXmlData() {
      // the list of jobs in the queue
      String data = "<workqueue>";
      synchronized (queue) {
         ListIterator i = queue.listIterator();
         while (i.hasNext()) {
            Job job = (Job) i.next();
            data += job.toXml();
         }
      }
      data += "</workqueue>";

      // the list of completed jobs
      data += "<completed>";
      synchronized (completedJobs) {
         ListIterator i = completedJobs.listIterator();
         while (i.hasNext()) {
            Job job = (Job) i.next();
            data += job.toXml();
         }
      }
      data += "</completed>";

      // the list of registered job consumers
      data += "<consumers>";
      synchronized (workers) {
         ListIterator i = workers.listIterator();
         while (i.hasNext()) {
            Worker worker = (Worker) i.next();
            data += worker.toXml();
         }
      }
      data += "</consumers>";
      return data;
   }

   public final Worker getWorker(String nodeId) {
      synchronized (workers) {
         ListIterator i = workers.listIterator();
         while (i.hasNext()) {
            Worker worker = (Worker) i.next();
            if (worker.getNodeId().equals(nodeId)) {
               return worker;
            }
         }
      }
      return null;
   }

   public final Worker createWorker(String nodeId) {
      synchronized (workers) {
         Worker worker = new Worker(this, port, nodeId);
         workers.add(worker);
         Thread t = new Thread(worker);
         t.setDaemon(true);
         t.start();
         return worker;
      }
   }

   public final void log(Job job) {
      synchronized (completedJobs) {
         completedJobs.add(0, job);
         if (completedJobs.size() > MAX_HISTORY_LENGTH) {
            completedJobs.remove(completedJobs.size() - 1);
         }
      }
   }

   public final void reap() {
      synchronized (workers) {
         ListIterator i = workers.listIterator();
         while (i.hasNext()) {
            Worker worker = (Worker) i.next();
            if (worker.expire()) {
               i.remove();
            }
         }
      }
   }

   public final void addJob() {
      synchronized (queue) {
         if (queue.size() < MAX_QUEUE_LENGTH) {
            Job job = new Job(Queue.getHostName() + " " + this.port,
                  this.nextJobId);
            this.nextJobId = this.nextJobId < 999 ? this.nextJobId + 1 : 1;
            queue.add(job);
            queue.notifyAll();
         }
      }
   }

   public final void addJob(Job job) {
      synchronized (queue) {
         queue.add(job);
         queue.notifyAll();
      }
   }

   public static final String getHostName() {
      try {
         final InetAddress addr = InetAddress.getLocalHost();
         return addr.getHostName();
      }
      catch (UnknownHostException e) {
         return "Unknown";
      }
   }
}
