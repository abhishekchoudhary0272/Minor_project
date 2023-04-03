Write-Host "Compiling all the files...."
$catalina_home = $Env:CATALINA_HOME
$all_java_files = Get-ChildItem -Path .\ -Filter *.java -Recurse -File -Name

# CMD method
# $compile = "javac -verbose -classpath $catalina_home\lib\*;$catalina_home\webapps\Agrify\WEB-INF\lib\*;$catalina_home\webapps\Agrify\WEB-INF\classes\;. $all_java_files"
# Start-Process cmd.exe -ArgumentList "/c $compile"

# Powershell method
$javac = "$Env:JAVA_HOME\bin\javac.exe"
$params = "-verbose", "-classpath", "$catalina_home\lib\*;$catalina_home\webapps\Agrify\WEB-INF\lib\*;$catalina_home\webapps\Agrify\WEB-INF\classes\;.", $all_java_files
& $javac @params

Write-Host "Starting server...."
Invoke-Expression "$catalina_home\bin\startup.bat"
Write-Host "Access at http://localhost:8080/Agrify"
