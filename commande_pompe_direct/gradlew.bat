@ECHO OFF
SET DIR=%~dp0
SET JAVA_EXE=java
"%JAVA_EXE%" -version >NUL 2>&1
IF %ERRORLEVEL% NEQ 0 (
  ECHO Java is required to run Gradle
  EXIT /B 1
)
IF NOT EXIST "%DIR%gradle\wrapper\gradle-wrapper.jar" (
  ECHO gradle-wrapper.jar missing; fetching Gradle 7.6.1 and generating wrapper...
  powershell -Command "Invoke-WebRequest https://services.gradle.org/distributions/gradle-7.6.1-bin.zip -OutFile '%DIR%gradle-7.6.1-bin.zip'"
  powershell -Command "Expand-Archive -Force '%DIR%gradle-7.6.1-bin.zip' '%DIR%'"
  "%DIR%gradle-7.6.1\bin\gradle.bat" wrapper --gradle-version 7.6.1
)
"%JAVA_EXE%" -jar "%DIR%gradle\wrapper\gradle-wrapper.jar" %*