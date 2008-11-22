while(true)
  begin
    print ">> "
    command = gets
    unless command.strip.empty?
      res = eval(command) 
      puts "=> #{res.inspect}"
    end
  rescue => e
    puts "#{e.backtrace.shift}: #{e} (#{e.class})"
    puts "\tfrom #{e.backtrace.join("\n\tfrom ")}"
  end
end
