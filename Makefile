agent: ReversiGame.class
	
Run.class: ReversiGame.java
	JAVA_HOME=/usr/usc/jdk/1.6.0_23 PATH=/usr/usc/jdk/1.6.0_23/bin:${PATH} javac ReversiGame.java

run: ReversiGame.class
	JAVA_HOME=/usr/usc/jdk/1.6.0_23/bin/ PATH=/usr/usc/jdk/1.6.0_23/bin:${PATH} java ReversiGame
