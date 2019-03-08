package com.example.mypc.xxxxx.bean;

public class Ask {


/**
 *
 * reqType : 0
 *perception : {"inputText":{"text":"你叫什么"}}
 * userInfo : {"apiKey":"52769b834a384670aa7fead99f048699","userId":"411528"}
 */

    private int reqType;
    private PerceptionBean perception;
    private UserInfoBean userInfo;

    public Ask(){

    }
    public Ask(PerceptionBean perception){
        this.perception=perception;
    }

    public int getReqType() {
        return reqType;
    }

    public void setReqType(int reqType) {
        this.reqType = reqType;
    }

    public PerceptionBean getPerception() {
        return perception;
    }

    public void setPerception(PerceptionBean perception) {
        this.perception = perception;
    }

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public static class PerceptionBean {

        //inputText:{"text"：你叫神马}


        private InputTextBean inputText;


        public PerceptionBean(InputTextBean inputText){
            this.inputText=inputText;
        }
        public InputTextBean getInputTextBean() {
            return inputText;
        }

        public void setInputTextBean(InputTextBean inputText) {
            this.inputText = inputText;
        }


        public static class InputTextBean{
            //你叫什么

            public InputTextBean(String text){
                this.text=text;
            }
            private String text;

            public String getText(){
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }


    }
    public static class UserInfoBean{
        //key id
        private String apiKey;
        private String userId;

        public String getApiKey() {
            return apiKey;
        }

        public void setApiKey(String apiKey) {
            this.apiKey = apiKey;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }
    }
}
