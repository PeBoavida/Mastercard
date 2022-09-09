# Getting Started

## Open a command line in this file directory

Type the following commands:

### Maven build the project:

mvnw clean package

### Docker build

docker build -t assignment-pb .

### Docker run

docker run -p 8080:8080 assignment-pb

### Access Swagger

Open a browser and enter the url: http://localhost:8080/swagger-ui/index.html