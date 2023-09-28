@echo off

F: && cd "F:\DAM\PR\Proyectos\HundirLaFlota"

cls && cd .\src\ && javac Interface.java -d ..\bin\ && cd .. && java -cp .\bin\ Interface