# EKS AWS

## Step 1

- Install AWS CLI
- Install kubectl
- Install eksctl
- Create user, userGroup (full admin permission)
- Configure key to access by CLI
- Test it with <code>aws eks list_cluster</code>
- To gather more information about the resource: <code>aws RESOURCE_TYPE help</code>

## Step 2

- Create a Cluster from the file cluster.yaml <code>eksctl create cluster -f cluster.yaml</code>
- Listing pods: <code>kubectl get pods -A</code>
- Create a Namespace from the file namespace.yaml <code>kubectl apply -f create namespace.yaml</code>
- Check it using: <code>kubectl get ns</code>
