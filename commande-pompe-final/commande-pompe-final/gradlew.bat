@ECHO OFF
SET DIR=%~dp0
SET JAVA_EXE=java
"%JAVA_EXE%" -version >NUL 2>&1
IF %ERRORLEVEL% NEQ 0 (
  ECHO Java is required to run Gradle
  EXIT /B 1
)
IF EXIST "%DIR%gradle\wrapper\gradle-wrapper.jar" (
  "%JAVA_EXE%" -jar "%DIR%gradle\wrapper\gradle-wrapper.jar" %*
) ELSE (
  ECHO gradle-wrapper.jar not found. CI will create wrapper before building.
  EXIT /B 1
)