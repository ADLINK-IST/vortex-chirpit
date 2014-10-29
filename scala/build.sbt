import AssemblyKeys._ // put this at the top of the file

assemblySettings

name		:= "chirpit"

version		:= "0.1.0"

organization 	:= "com.chirpit"

homepage :=  Some(new java.net.URL("http://prismtech.com"))

scalaVersion 	:= "2.11.3"

resolvers += "nuvo.io maven repo" at "http://nuvo-io.github.com/mvn-repo/snapshots"

resolvers += "Vortex Snapshot Repo" at "https://dl.dropboxusercontent.com/u/19238968/devel/mvn-repo/vortex"

libraryDependencies += "io.nuvo" % "moliere_2.10" % "0.5.0-SNAPSHOT"

libraryDependencies += "com.prismtech.cafe" % "cafe" % "2.1.0-SNAPSHOT"

autoCompilerPlugins := true

scalacOptions += "-deprecation"

scalacOptions += "-unchecked"

scalacOptions += "-optimise"

scalacOptions += "-feature"

scalacOptions += "-language:postfixOps"

proguardSettings

ProguardKeys.options in Proguard += """
-dontnote
-dontwarn
-ignorewarnings
-dontobfuscate
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-keeppackagenames **
-optimizationpasses 3
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*,!code/allocation/variable
-keep public class org.opensplice.mobile.core.ServiceEnvironmentImpl
-keep public class org.slf4j.ILoggerFactor {
      *;
}
-keep  class dds.demo.*Helper {  
       *; 
}
"""

// ProguardKeys.options in Proguard += ProguardOptions.keepMain("dds.demo.oximeter.Oximeter")
