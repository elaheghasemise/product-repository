
This is simple rest API which use in-memory mongoDB . it filles mongoDB by reading data from jsondata.txt

To run the Tests:
ProductRestTestHandler is component test so need to have app up and run.
ProductServiceTest is unitTest which it runs spring context initially.

Docker:
To run this project,go to project root and run these commands:
docker build -f DockerFile -t productrepositorydemo .
docker run -p 8080:8080 productrepositorydemo



