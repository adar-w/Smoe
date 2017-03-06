

# Rda
> Easy access db

> 开箱即用DB工具箱

* [x] mysql-connector.version > 5.1.33 


#### 使用流程
    1. 确认你想访问的数据库.
    2. 建立对应的DO对象.
    3. 请如下使用Rda.


#### 例子看我
    me.smoe.rda.TestRda

    
#### 仅需的配置
    private static final String URL = "jdbc:mysql://localhost:3306/adar";
    static {
        Rda.to(URL, "root", "adar");
    }


#### SQL
    @Test
    public void sql() {
        Long count = Rda.build("select count(*) from rdo").getLong();
        System.out.println(count);
        
        String name = Rda.build("select name from rdo where id = ?", 1).getString();
        System.out.println(name);

        Rdo rdo = Rda.build("select * from rdo where id = ?", 2).mapping(Rdo.class);
        System.out.println(rdo);
        
        List<Rdo> rods = Rda.build("select * from rdo").mappings(Rdo.class);
        System.out.println(rods);
    }


#### Save
    @Test
    public void save() {
        Rdo rdo = new Rdo();
        rdo.setId(3L);
        rdo.setName("hah");
        
        Rda.at(Rdo.class).save(rdo);
    }


#### Modify
    @Test
    public void modify() {
        Rdo rdo = Rda.at(Rdo.class).findOne(2L);
        rdo.setName("modify");
        
        Rda.at(Rdo.class).modify(rdo);
    }


#### FindOne
    @Test
    public void findOne() {
        Long id = 630005L;
        
        Rdo rdo = Rda.at(Rdo.class).findOne(id);
        
        Assert.isTrue(id.equals(rdo.getId()));
    }
    
    
#### FindAll
    @Test
    public void findAll() {
        for (Rdo rdo : Rda.at(Rdo.class).findAll()) {
            System.out.println(rdo.getId() + " + " + rdo.getName());
        }
    }


#### FindMatch  
    @Test
    public void findMatch() {
        System.out.println(Rda.at(Rdo.class).limit(2).find(new Rdo("hah")));
    }


#### Delete
    @Test
    public void delete() {
        Rda.at(Rdo.class).delete(1L);
    }


#### DeleteAll  
    @Test
    public void deleteAll() {
        Rda.at(Rdo.class).deleteAll();
    }

#### Count  
    @Test
    public void count() {
        System.out.println(Rda.at(Rdo.class).count());
    }
    
    
#### CountMatch
    @Test
    public void countMatch() {
        System.out.println(Rda.at(Rdo.class).count(new Rdo("hehe")));
    }


#### Exists 
    @Test
    public void exists() {
        System.out.println(Rda.at(Rdo.class).exists(1L));
    }

#### More
    For you.