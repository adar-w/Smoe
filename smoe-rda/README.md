

# Rda
> Easy access db

* [x] mysql-connector.version > 5.1.33 


## Just config
	private static final String URL = "jdbc:mysql://localhost:3306/adar";
	static {
		Rda.to(URL, "root", "adar");
	}


## Save
	@Test
	public void save() {
		Rdo rdo = new Rdo();
		rdo.setId(3L);
		rdo.setName("hah");
		
		Rda.at(Rdo.class).save(rdo);
	}


## Modify
	@Test
	public void modify() {
		Rdo rdo = Rda.at(Rdo.class).findOne(2L);
		rdo.setName("modify");
		
		Rda.at(Rdo.class).modify(rdo);
	}


## FindOne
	@Test
	public void findOne() {
		Long id = 630005L;
		
		Rdo rdo = Rda.at(Rdo.class).findOne(id);
		
		Assert.isTrue(id.equals(rdo.getId()));
	}
	
	
## FindAll
	@Test
	public void findAll() {
		for (Rdo rdo : Rda.at(Rdo.class).findAll()) {
			System.out.println(rdo.getId() + " + " + rdo.getName());
		}
	}


## FindMatch	
	@Test
	public void findMatch() {
		System.out.println(Rda.at(Rdo.class).limit(2).find(new Rdo("hah")));
	}


## Delete
	@Test
	public void delete() {
		Rda.at(Rdo.class).delete(1L);
	}


## DeleteAll	
	@Test
	public void deleteAll() {
		Rda.at(Rdo.class).deleteAll();
	}

## Count	
	@Test
	public void count() {
		System.out.println(Rda.at(Rdo.class).count());
	}
	
	
## CountMatch
	@Test
	public void countMatch() {
		System.out.println(Rda.at(Rdo.class).count(new Rdo("hehe")));
	}


## Exists	
	@Test
	public void exists() {
		System.out.println(Rda.at(Rdo.class).exists(1L));
	}


## TestCase
	me.smoe.rda.TestRda