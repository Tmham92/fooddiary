# README #

### Repository setup ###
---

## Fork this repository ##
1. From the repository, click + in the leftmost global sidebar and select "Fork this repository" under "Get to work".
2. In the Fork dialog, define the options for your fork.
---
## Clone forked repository ##
1. In your forked repository click "clone" in the top right.
2. Copy the "git clone" command in the terminal (this will create the repository directory in the current directory)
---
## IntelliJ git usage ##

**commit and push:**

1. Top right of your IntelliJ screen there is a "git: " section. Clicking on the _green_ checkmark(Ctrl + K) will open the commit window.
2. In the commit window:
	- select the files you want to commit.
	- add a good commit message.
	- clicking commit will commit the changes without pushing them, clicking the dropdown menu arrow on the commit button gives you the option to commit and push

**Add the upstream master repository to the remote hosts:**

- Go to the _upstream master repository_ on [bitbucket](bitbucket.org/)
- Click "Clone" in the top right.
- Copy the link after "git clone " (git@...).


1. Click "VCS" in the top bar.
2. Click "Git".
3. Click "Remotes..."
4. On the popup click "+".
5. Fill in a name in "Name: " (i.e. Upstream).
6. Paste the link you copied earlier in "URL: ".
7. Click "OK".
	- You should see two remotes in the list, origin (your forked repository) and the remote you just added.

**Fetching updates from the upstream master repository:**

***Always create a backup before merging so you don't accidentally lose progress!***

1. After creating a backup! (i.e. copy paste the repository in a different directory.)
2. Click "VCS" in the top bar.
3. Click "Git".
4. Click "Fetch".
5. Go to the same menu and click "Merge changes".
6. In "branches to merge" select the upstream repository.
7. Click "Merge".

---
## Create a pull request for the _upstream master repository_ ##

***Always try to keep up to date with the upstream master repository!***

1. Go to your forked repository on [bitbucket](bitbucket.org/)
2. In the sidebar click "Pull requests"
3. In the new window click "Create pull request" in the topright.
4. Give the request a title and description of the work that you want to put into the master repository.
	+ **DO NOT** select "Close master after the pull request is merged".
	
5. Click "Create pull request"

The pull request will be reviewed and if everything is good it will be added to the master repository.
After something is added to the master repository you should fetch the updates so make sure you wont stay behind in commits which can cause conflicts.

---