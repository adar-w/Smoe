

# Cda
> Easy http request.


## Get text
	String text = Cda.connect("https://github.com/adar-w").text();
	System.out.println(text);


##	Download pic:
	Cda.connect("https://github.com/adar-w").download("/Users/adar-w/1.jpg");
		

##	Post request:
	String text = Cda.connect("https://github.com/adar-w").post().formdata("key", "1717").text();


## Proxy:
	String content = Cda.connect(https://github.com/adar-w).proxy("1.2.3.4", 7777, "username", "pwd").text();


##	More: 
	For yourself
