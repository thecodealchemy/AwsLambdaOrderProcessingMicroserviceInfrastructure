LambdaOrderProcessingMicroserviceInfrastructure




GithubRole:


```
aws iam create-role --role-name GitHubActionsDeployRole `
--assume-role-policy-document file://src/main/resources/github-actions-trust-policy.json `
--description "Allows GitHub Actions to deploy LambdaMicroserviceDemo"
```

```aiignore
aws iam create-policy-version `
--policy-arn arn:aws:iam::135458291619:policy/CloudFormationDeploymentPolicy `                                                                                                 
--policy-document file://src/main/resources/cloudformation-deployment-policy.json `                                                                                            
--set-as-default                                                                                                                                                               
```
```
aws iam create-policy-version `
--policy-arn arn:aws:iam::135458291619:policy/CloudFormationDeploymentPolicy `                                                                                                 
--policy-document file://src/main/resources/cloudformation-deployment-policy.json `                                                                                            
--set-as-default
```
