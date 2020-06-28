Food Diary Application
==================

The goal of this project is to make a viable, user-friendly web-based application to receive and store food diaries. 
Food diaries are a way to track a participant’s food intake for research in nutrition and dietetics.


### Description
The goal of this project is to make a viable, user-friendly web-based application to receive and store food diaries. 
Food diaries are a way to track a participant’s food intake for research in nutrition and dietetics. 
Participants write down exactly what, when and how much they ate or drank in these diaries, giving a complete view of the participant’s intake. 
The current state of handling the data is very time consuming. Researches receive handwritten diaries, 
which they enter in an online application that stores the information and calculates the nutritional values. 
In order to speed up this process and minimalize potential mistakes a new application has been made. 
By ensuring that the participants can fill in his food diaries in a clear, quick and simple manner, the researchers have less work, and the chances of mistakes are minimized. 
For researchers it is not always clear in advance which variables and relationships are important. Therefore, the application data output is made highly customizable.

---

# Installation

### Fork this repository
1. From the repository, click + in the leftmost global sidebar and select "Fork this repository" under "Get to work".
2. In the Fork dialog, define the options for your fork.

### Clone forked repository
1. In your forked repository click "clone" in the top right.
2. Copy the "git clone" command in the terminal (this will create the repository directory in the current directory)

# IntelliJ git usage

**commit and push:**

1. Top right of your IntelliJ screen there is a "git: " section. Clicking on the _green_ checkmark(Ctrl + K) will open the commit window.
2. In the commit window:
	- select the files you want to commit.
	- add a good commit message.
	- clicking commit will commit the changes without pushing them, clicking the dropdown menu arrow on the commit button gives you the option to commit and push

**Add the upstream repository to the remote hosts:**

- Go to the _upstream repository_ on [bitbucket](bitbucket.org/)
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

**Pulling updates from the upstream master repository:**

***Always create a backup before merging so you don't accidentally lose progress!***

1. After creating a backup! (i.e. copy paste the repository in a different directory.)
2. Click "VCS" in the top bar.
3. Click "Git".
4. Click "Pull".
5. Click "Remote:" and select the upstream repository.
6. In "Branches to merge:" select "upstream/master"
7. Click "Pull" on the bottom left of the pup-up.

If merge conflicts occur after pulling:

1. Click "VCS".
2. Click "Git".
3. Click "Merge Changes..."
4. Select the upstream changes.
5. Click merge, this will bring up a window where you can solve the merge conflicts.

---

# Authors
Students of the Hanzehogeschool:

- Hans Zijlstra
- Hugo Donkerbroek
- Tobias Ham
- Tom Wagenaar

# Acknowledgments
This project is being done in collaboration with dr. Berber Vlieg-Boerstra, senior researcher dietitian, Maaike Smelt, teacher Life Sciences and Annemarie Brummelman, teacher Nutrition and Dietetics,
teacher and project leader Michiel Noback