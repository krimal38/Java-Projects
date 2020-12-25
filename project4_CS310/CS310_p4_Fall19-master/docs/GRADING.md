## Grading Rubric
[back](README.md)

### No Credit
- Non submitted assignments
- Non compiling assignments
- Non-independent work
- "Hard coded" solutions
- Code that would win an obfuscated code competition with the rest of CS310 students.

### How will my assignment be graded?
- Grading will be divided into two portions:
  1. Manual/Automatic Testing (100%): To assess the correctness of programs.
  2. Manual Inspection (30% off the top points): A checklist of features your programs should exhibit. These comprise things that cannot be easily checked via unit tests such as good variable name selection, proper decomposition of a problem into multiple functions or cooperating objects, overall design elegance, and proper asymptotic complexity. These features will be checked by graders and assigned credit based on level of compliance.
- You CANNOT get points (even style/manual-inspection points) for code that doesn't compile or for submitting just the files given to you with the assignment. You CAN get manual inspection points for code that (a) compiles and (b) is an "honest attempt" at the assignment, but does not pass any unit tests.
- Extra credit for early submissions:
  - 1% extra credit rewarded for every 24 hours your submission made before the due time
  - Up to 5% extra credit will be rewarded
  - Your latest submission before the due time will be used for grading and extra credit checking.  You CANNOT choose which one counts.

#### Manual/Automatic Testing (100%)
- We will run unit tests on your classes, and your simulator will be run and play through Dijkstra's on several different graphs.
- We will create a 100 node graph where all nodes have a 25% chance of being connected. If your code takes more than one minute to create such a graph, or if your code takes more than one minute to setup the routing tables on such a graph, you will lose some points (see below). We promise to run it on a reasonably modern laptop (not a tablet, for instance).

#### Manual Code Inspection Rubric (30% "off the top" points)
These are all "off the top" points (i.e. items that will lose you points rather than earn you points):

Inspection Point | Points | High (all points) | Med (1/2 points) | Low (no points)
:---: | :---: | :--- | :--- | :--- 
Submission Format (Folder Structure) |  5 |  Code is in a folder which in turn is in a zip file. Folder is correctly named. | Code is not directly in user folder, but in a sub-folder. Folder name is correct or close to correct. | Code is directly in the zip file (no folder) and/or folder name is incorrect.
Efficiency | 5 | Code can generate a 100 node graph with 25% connection probability in under one minute (`SimGUI 100 0.25` with any seed) | Code can generate such a graph or route on such a graph in the time given, but not both. | Code cannot generate or cannot route on such a graph as described in the time limit given.
Code Style Basics | 10 | Code passes all checks for cs310code.xml [and] Code has a set indentation and formatting style which is kept consistent throughout and code looks "well laid out".) [and] Code has good, meaningful variable, method, and class names. | Code passes all checks for cs310code.xml [but] Code looks "messy" and/or names often have single letter identifiers and/or incorrect/meaningless identifiers. (Note: i/j/k acceptable for indexes.) | Code does not pass cs310code.xml checks.
JavaDocs | 10 | Code passes all checks for cs310comments.xml [and] The entire code base is well documented with meaningful comments in JavaDoc format. Each class, method, and field has a comment describing its purpose. Occasional in-method comments used for clarity. | Code passes all checks for cs310comments.xml [and] The code base has some comments, but is lacking comments on some classes/methods/fields or the comments given are mostly "translating" the code. | Code does not passes all checks for cs310comments.xml [and/or] The only documentation is what was in the template and/or documentation is missing from the code (e.g. taken out).
