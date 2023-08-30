![Last Workflow](https://github.com/kamentr/java-course/actions/workflows/gradle.yml/badge.svg)

# Weather & Climate API

API for retrieving weather and climate data for the last 60 years.
Create charts and tables with city or country average temperatures over the years.
This project aims to provide visual representation of Climate change.

[Tasks Board](https://github.com/users/kamentr/projects/1/views/1)

# Deployment

Run ```App Engine``` workflow to deploy to https://climate-api-390915.lm.r.appspot.com/
`gcloud app deploy --quiet app.yaml --promote
`

# Documentation

Api docs can be found in http://localhost:8080/swagger-ui/index.html#/

# Docker

### Build

```
docker build -t climate-api
```

### Run

``` 
docker run -p 8080:8080 climate-api
```
