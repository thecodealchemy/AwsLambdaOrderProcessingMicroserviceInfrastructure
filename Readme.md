LambdaOrderProcessingMicroserviceInfrastructure




GithubRole:


```
aws iam create-role --role-name GitHubActionsDeployRole `
--assume-role-policy-document file://src/main/resources/github-actions-trust-policy.json `
--description "Allows GitHub Actions to deploy LambdaMicroserviceDemo"
```
