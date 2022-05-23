@echo off

git add .
git commit -a -F %1
git tag %2
git push