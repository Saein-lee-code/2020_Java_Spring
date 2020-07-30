<%	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	String url="jdbc:oracle:thin:@localhost:1521:orcl";
	String userName = "hr";
	String password = "koreait2020";
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;	
	
	String sql = "SELECT * FROM COUNTRIES";
	List<CountriesVO> list = new ArrayList();
	try{
		con = DriverManager.getConnection(url, userName, password);
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();		
		while(rs.next()){
			String country_id = rs.getString("country_id");
			String country_name = rs.getString("country_name");
			int region_id = rs.getInt("region_id");
			
			CountriesVO vo = new CountriesVO();
			vo.setCountry_id(country_id);
			vo.setCountry_name(country_name);
			vo.setRegion_id(region_id);
			list.add(vo);
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			rs.close();
			ps.close();
			con.close();
		}
		catch(Exception e){}
	}
%>
