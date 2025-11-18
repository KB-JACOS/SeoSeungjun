class programmers_신규_아이디_추천 {
    
    public String solution(String new_id) {
        
        String id = new_id.toLowerCase();           //1단계
        
        id = removeWrong(id);                       //2단계
        id = removeDot(id);                         //3단계
        id = removeEndDot(id);                      //4단계

        if(id.isEmpty()) {                          //5단계
            id += "a";
        }        
        
        id = fitLength(id);                         //6단계
        
        if(id.length() <= 2) {                      //7단계
            while(id.length() < 3) {
                id += id.charAt(id.length() - 1);
            }
        }
        
        
        return id;
        
    }
    
    public String fitLength(String id) {
        if(id.length() > 15) {
            id = id.substring(0, 15);
        }
        
        id = removeEndDot(id);
        
        return id;
    }
    
    public String removeEndDot(String id) {
        
        StringBuilder sb = new StringBuilder(id);
        
        if(sb.charAt(0) == '.') {
            sb.deleteCharAt(0);
        }
        
        if(sb.length() != 0 && sb.charAt(sb.length() - 1) == '.') {
            sb.deleteCharAt(sb.length() - 1);
        }
        
        return sb.toString();
    }
    
    public String removeDot(String id) {
        char[] arr = id.toCharArray();        
        boolean findDot = false;
        int idx = 0;
        
        StringBuilder sb = new StringBuilder();

        while(true) {
            if(idx == arr.length) {
                break;
            }
            
            if(arr[idx] == '.') {
                idx++;
                if(findDot) { continue; }
                findDot = true;
                
            }
            
            else {
                idx++;
                if(findDot) { findDot = false; }
            }
            
            sb.append(arr[idx-1]);
        }
        
        System.out.println(sb.toString());
        return sb.toString();
    }
    
    public String removeWrong (String id) {
        char[] arr = id.toCharArray();
        
        int idx = 0;
        for(int i=0;i<id.length();i++) {
            if(arr[i] >= 'a' && arr[i] <= 'z') {
                arr[idx] = arr[i];
                idx ++;
            }
            
            else if(arr[i] >= '0' &&  arr[i] <= '9') {
                arr[idx] = arr[i];
                idx ++;
            }
            
            else if (arr[i] == '.' || arr[i] == '-' || arr[i] == '_') {
                arr[idx] = arr[i];
                idx ++;
            }
            
        }
        
        return new String(arr, 0, idx);
    }
}