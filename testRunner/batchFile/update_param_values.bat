@echo off
SETLOCAL ENABLEDELAYEDEXPANSION

REM ----------------------------
REM 1️⃣ Set workspace
REM ----------------------------
cd /d C:\Study\Selenium\workspace\amazon-selenium-framework

REM ----------------------------
REM 2️⃣ Set Jenkins parameters with defaults
REM ----------------------------
IF "%BROWSER%"=="" SET BROWSER=chrome
IF "%SERVER%"=="" SET SERVER=dev
IF "%PARALLEL_MODE%"=="" SET PARALLEL_MODE=false
IF "%MAX_PARALLEL_TESTS%"=="" SET MAX_PARALLEL_TESTS=1
IF "%MAX_RETRIES%"=="" SET MAX_RETRIES=0
IF "%SUITE%"=="" SET SUITE=sanity
IF "%RUN_MODE%"=="" SET RUN_MODE=local
echo ------------------------------
echo Jenkins Build Parameters
echo ------------------------------
echo BROWSER=%BROWSER%
echo ENV=%SERVER%
echo PARALLEL_MODE=%PARALLEL_MODE%
echo MAX_PARALLEL_TESTS=%MAX_PARALLEL_TESTS%
echo MAX_RETRIES=%MAX_RETRIES%
echo SUITE=%SUITE%
echo RUN_MODE=%RUN_MODE%
echo ------------------------------

REM ----------------------------
REM 3️⃣ Update config.properties safely
REM ----------------------------
SET configFile=src\test\resources\configuration\config.properties

(for /F "usebackq tokens=1,* delims==" %%A in ("%configFile%") do (
    set "key=%%A"
    set "value=%%B"

    if /I "!key!"=="BROWSER" (
        echo BROWSER=%BROWSER%
    ) else if /I "!key!"=="ENV" (
        echo ENV=%SERVER%
    ) else if /I "!key!"=="RUN_MODE" (
        echo RUN_MODE=%RUN_MODE%
	) else if /I "!key!"=="PARALLEL_MODE" (
        echo PARALLEL_MODE=%PARALLEL_MODE%
    ) else if /I "!key!"=="MAX_PARALLEL_TESTS" (
        echo MAX_PARALLEL_TESTS=%MAX_PARALLEL_TESTS%
    ) else if /I "!key!"=="MAX_RETRIES" (
        echo MAX_RETRIES=%MAX_RETRIES%
    ) else (
        echo !key!=!value!
    )
)) > "%configFile%.tmp"

move /Y "%configFile%.tmp" "%configFile%" > nul

REM ----------------------------
REM 4️⃣ Run Maven dynamically passing suite
REM ----------------------------
REM -DsuiteXmlFile allows Maven to pick up the suite without modifying pom.xml
mvn clean test -DsuiteXmlFile=%SUITE%

ENDLOCAL
