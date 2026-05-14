@echo off
@rem A script to build docs from markdown to html using markdown-it-py command line tools

echo Building md to html

cd docs
markdown-it index.md > index.html

cd fabric
markdown-it index.md > index.html

cd ../neoforge
markdown-it index.md > index.html

cd ../../

echo completed
pause