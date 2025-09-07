class programmers_스킬트리 {
    
    /**
     * 프로그래머스 - 스킬트리
     */
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;        
        
        char[] skillSeq = skill.toCharArray();
        
        int skill_length = skill.length();
        
        
        
        for(String tree : skill_trees){
            int tree_seq = 0;
            boolean check = true; 
            
            for(int i=0;i<tree.length();i++){
                
                
                if(skill.substring(tree_seq + 1).contains(Character.toString(tree.charAt(i)))){
                    System.out.println("fail case: " + skill.substring(tree_seq) + " " + tree.charAt(i));
                    check = false;
                    break;
                }
                
                
                
                else{
                    System.out.println("case: " + skill.substring(tree_seq) + " " + tree.charAt(i));
                }
                if(skillSeq[tree_seq] == tree.charAt(i) && tree_seq != skill_length-1){
                    
                    tree_seq++;
                }

            }
            
            if(check) answer++;
            
        }
        
        return answer;
        
        
        

    }
}