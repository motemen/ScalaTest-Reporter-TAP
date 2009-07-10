#!rake
# http://github.com/cho45/GAE-Scala-Template/blob/hookhub/Rakefile

require 'rake'
require 'rake/clean'
require 'pathname'
require 'fileutils'

include FileUtils::Verbose

CLEAN.include [%w[classes]]

@classpath  = (ENV['CLASSPATH'] || '').split(':')
@classpath << Pathname.glob("#{ENV['HOME']}/lib/java/**/*.jar")
@classpath << Pathname.glob("#{ENV['HOME']}/lib/scala/**/*.jar")
@classpath << 'classes'
@classpath.flatten!
ENV['CLASSPATH'] = @classpath.join(':')

def files (ext = '', pre = '')
  FileList['src/**/*.scala'].map do |i|
    pre + i.sub(/^src\//, '').sub(/\..+$/, ext)
  end
end

def scala (*args)
  sh 'scala', '-classpath', ENV['CLASSPATH'], *args
end

def scalac (*args)
  sh 'scalac', '-classpath', ENV['CLASSPATH'], *args
end

task :default => :build

task :build       => files('.class', 'classes/')

directory 'classes'
files.each do |s|
  file "classes/#{s}.class" => [ 'classes', "src/#{s}.scala" ] do |t|
    scalac '-d', 'classes', "src/#{s}.scala"
  end
end
