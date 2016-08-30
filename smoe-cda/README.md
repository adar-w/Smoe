

# Cdar
> Easy http request.


## Usage
Get text:
	String text = Cdar.connect("https://github.com/adar-w").text();
	System.out.println(text);


Download pic
	Cdar.connect("https://github.com/adar-w").download("/Users/adar-w/1.jpg");
		

Post request:
	String text = Cdar.connect("https://github.com/adar-w").post().formdata("key", "1717").text();


Proxy:
	String content = Cdar.connect(https://github.com/adar-w)
						  .proxy("1.2.3.4", 7777, "proxyAccount", "pwd");
					 	  .text();


More: 
	For yourself
