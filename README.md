# template-code-generator
Scripts for automatic microservices code initiation
After git clone for the project sources, switch to microservice-generator/src. And than:

./generate-ms-sceleton.kts ../resources/ms-template/__name__ {{output directory}} {{name-part-1}} {{name-part-2}}

Example:
./generate-ms-sceleton.kts ../resources/ms-template/__name__ /tmp/test logs generator

This command will create a new service log-generator under directory /tmp/test.

In order to generate something that works, set output directory as gaia-full and then add the new project to settings.gradle file
