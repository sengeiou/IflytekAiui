package com.zhengpu.iflytekaiui.iflytekbean;

import java.util.List;

/**
 * sayid ....
 * Created by wengmf on 2018/1/2.
 */

public class RobotCommandBean {


    /**
     * category : OPENAPPTEST.RobotCommand:4.0
     * intentType : custom
     * query : 前进
     * query_ws : 前进/VI//
     * rc : 0
     * nlis : true
     * service : OPENAPPTEST.RobotCommand
     * uuid : atn00136bd0@ch6e7f0da697996f2001
     * vendor : OPENAPPTEST
     * version : 4.0
     * semantic : [{"entrypoint":"ent","intent":"command","score":1,"slots":[{"begin":0,"end":2,"name":"data","normValue":"前进","value":"前进"}]}]
     * state : null
     * sid : atn00136bd0@ch6e7f0da697996f2001
     * text : 前进
     */

    private String category;
    private String intentType;
    private String query;
    private String query_ws;
    private int rc;
    private String nlis;
    private String service;
    private String uuid;
    private String vendor;
    private String version;
    private Object state;
    private String sid;
    private String text;
    private List<SemanticBean> semantic;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntentType() {
        return intentType;
    }

    public void setIntentType(String intentType) {
        this.intentType = intentType;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getQuery_ws() {
        return query_ws;
    }

    public void setQuery_ws(String query_ws) {
        this.query_ws = query_ws;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public String getNlis() {
        return nlis;
    }

    public void setNlis(String nlis) {
        this.nlis = nlis;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<SemanticBean> getSemantic() {
        return semantic;
    }

    public void setSemantic(List<SemanticBean> semantic) {
        this.semantic = semantic;
    }

    public static class SemanticBean {
        /**
         * entrypoint : ent
         * intent : command
         * score : 1
         * slots : [{"begin":0,"end":2,"name":"data","normValue":"前进","value":"前进"}]
         */

        private String entrypoint;
        private String intent;
        private int score;
        private List<SlotsBean> slots;

        public String getEntrypoint() {
            return entrypoint;
        }

        public void setEntrypoint(String entrypoint) {
            this.entrypoint = entrypoint;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<SlotsBean> getSlots() {
            return slots;
        }

        public void setSlots(List<SlotsBean> slots) {
            this.slots = slots;
        }

        public static class SlotsBean {
            /**
             * begin : 0
             * end : 2
             * name : data
             * normValue : 前进
             * value : 前进
             */

            private int begin;
            private int end;
            private String name;
            private String normValue;
            private String value;

            public int getBegin() {
                return begin;
            }

            public void setBegin(int begin) {
                this.begin = begin;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNormValue() {
                return normValue;
            }

            public void setNormValue(String normValue) {
                this.normValue = normValue;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
