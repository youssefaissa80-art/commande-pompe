@ECHO OFF
SET DIR=%~dp0
SET JAVA_EXE=java
"%JAVA_EXE%" -version >NUL 2>&1
IF %ERRORLEVEL% NEQ 0 (
  ECHO Java is required to run Gradle
  EXIT /B 1
)
IF NOT EXIST "%DIR%gradle\wrapper\gradle-wrapper.jar" (
  ECHO gradle-wrapper.jar missing; generating wrapper...
  powershell -Command "Invoke-WebRequest https://services.gradle.org/distributions/gradle-8.2-bin.zip -OutFile '%DIR%gradle-8.2-bin.zip'"
  powershell -Command "Expand-Archive -Force '%DIR%gradle-8.2-bin.zip' '%DIR%'"
  "%DIR%gradle-8.2\bin\gradle.bat" wrapper --gradle-version 8.2
)
"%JAVA_EXE%" -jar "%DIR%gradle\wrapper\gradle-wrapper.jar" %*