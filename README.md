# Java Fun

#### A sample REST API application

Services are implemented with SpringBoot.

## Deployment

Interface consists from 2 services for User and Admin API relatively.
API split for the following reasons:
- configurability
- security
- maintainability

### Main services are:
- rest-admin-service
- rest-user-service

## TODO
- implement UsersRepo with Redis
- implement Integration tests

## DevOps TODO
- configure TravisCI build
- configure K8s
