## Build images

#Declare a string array
ImagesArray=("user-rest-api-service"  "admin-rest-api-service")
for image in ${ImagesArray[*]}; do
  ## TAG docker images
  docker tag $image:latest $image:$SHA
  docker tag $image:latest $image:$MVN_VERSION

  ## Push images to docker
  docker push $image:latest
  docker push $image:$GIT_SHA
  docker push $image:$MVN_VERSION
done

## Apply k8s configuration
kubectl apply -f k8s

## Push new version to k8s
kubectl set image deployments/
