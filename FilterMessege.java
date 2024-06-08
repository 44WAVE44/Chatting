public class FilterMessege {
    
    public String check_messege(String s) throws Exception {
		
		String[] messege = s.split(" ");
		String[] banned_words = {"green","blue","pink"};
		StringBuilder cleanMessege = new StringBuilder();
		String[] TEST = new String[messege.length];

	
		for(int i = 0;i<messege.length;i++) {
			
			for(int j = 0;j<banned_words.length;j++) {
				
				if(messege[i].equalsIgnoreCase(banned_words[j])) {
					TEST[i] = "Filterd";
				}
			}
			
			if(TEST[i]==null) {
				TEST[i] = messege[i];
			}
		}
		
		for(int i =0 ; i<TEST.length;i++) {
			cleanMessege.append(TEST[i]+" ");
		}
		
		return cleanMessege.toString();
	}

}
