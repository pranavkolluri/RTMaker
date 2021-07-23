# Gitlet Design Document

Pranav Kolluri:

## Classes and Data Structures
Commits: Data structures that hold two parents, hashID, date, fileMap
HeadMap: Tracks current head Hash and current branch name
BranchTracker: Map that tracks branch names and branch hashes
FileMap: Tracks the file names in a commit and the hash values for those files

Commit handles all commit access functions that do not involve fileIO
Repository handles file IO, staging, and removal.
CheckOut handles all checkouts and reset.
Logs handles status and log/global-log
Merge handles merging, includes a file conflict modifier, split-point finder, and merge itself.


## Algorithms:


## Persistence

