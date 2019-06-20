@echo off
chcp 65001
for /f "tokens=*" %%a in ('dir /b "*.jar"') do (
set var=%%a
)
java -jar %var% --logging.config=config/logback-spring.xml