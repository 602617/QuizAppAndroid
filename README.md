# README: Committing and Pushing to dev-branch

## Prerequisites
Ensure you have the following installed and set up on your system:
- Git
- Access to the remote repository
- An SSH key or HTTPS access configured for GitHub, GitLab, or other Git services

## Steps to Commit and Push to dev-branch

### 1. Navigate to Your Project Directory
Open your terminal or command prompt and navigate to the directory of your project:
```sh
cd /path/to/your/project
```

### 2. Check Out the dev-branch
If you're not already on the `dev-branch`, switch to it by running:
```sh
git checkout dev-branch
```

If the branch doesn't exist locally, create it by running:
```sh
git checkout -b dev-branch
```

### 3. Stage Your Changes
Add the files you want to commit by staging them. You can stage all files by running:
```sh
git add .
```
Or you can stage specific files:
```sh
git add path/to/file1 path/to/file2
```

### 4. Commit Your Changes
Create a commit with a descriptive message:
```sh
git commit -m "Your commit message here"
```
Make sure the message describes the changes you made clearly.

### 5. Push Your Changes to dev-branch
Push the changes to the remote `dev-branch`:
```sh
git push origin dev-branch
```

### 6. Verify the Push
You can verify that your changes have been pushed by visiting the repository on the remote platform (e.g., GitHub, GitLab) and checking the `dev-branch`.

### 7. Troubleshooting Common Issues
#### a. Authentication Issues
Ensure that your SSH key or HTTPS credentials are set up correctly. Run the following command to test your SSH connection:
```sh
ssh -T git@github.com
```

#### b. Merge Conflicts
If you encounter merge conflicts while pushing, resolve them locally and commit the resolved changes before pushing again.

### 8. Optional: Pull the Latest Changes from dev-branch
To stay up to date with the latest changes on `dev-branch`, pull before making new commits:
```sh
git pull origin dev-branch
```

