The objective of this thesis is the creation of a small-scale replica of an information
system of the textile industry and to be able to carry out a performance and latency
analysis between client and server. Based on the study of the environment and the use
of clients for the web service, the objective is to find solutions that give a better user
experience for the client, as well as the possibility of reducing waiting times, latency
between communications, and connections to the database.


During the study of the use of this service in a real environment, it has been verified that
clients can insert large amounts of information into databases, which causes long waiting
times until they obtain a response from the web service of the operation performed.
Through new technologies, this project seeks to allow long processes to be carried out
asynchronously to our backend and allow the client to continue browsing the application
without interrupting its use.
An analysis of Machine Learning techniques is also carried out that allows us to cache the
most probable queries of a user with the intention of reducing waiting times by avoiding
access to the web service database.
To achieve the objectives set out in this work, the next steps have been followed:

• Perform an analysis of external cases with a certain similarity in terms of the
problems to be solved. Investigate different approaches for similar problems and
propose possible applications of these solutions to our project.

• Address the application design process, specifying the problems to be solved and
making decisions regarding the structure of the solutions to be proposed and the
structure of the complete service.

• Carry out an analysis of the state of the art of the different modules in the project.
These include the selection of the different technologies to address the problems,
the tools for the creation of the web service and deployment in the network.

• Design and implementation of the previously proposed solution. A web service
architecture design is proposed together with the technologies used for deployment.
A design of the machine in charge of data prediction for cache storage is also carried
out, and finally, the different communication techniques used between client and
server to reduce the latency of our service. Regarding the implementation, the
creation of the different modules separately and the union of all of them to form a
single functional system is described.

• Performance analysis of the proposed solutions. Different metrics and measurements
of the proposed implementation have been made for different types of user that
would use the application in a real environment and the different scenarios in which
the service could work have been studied. The strengths of the new implementations
are analyzed in different scenarios in order to establish which ones obtain better
results.
