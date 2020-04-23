package per.colin.sphtest.http;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class QueryDataResp extends BaseResponse {

    /**
     * help : https://data.gov.sg/api/3/action/help_show?name=datastore_search
     * success : true
     * result : {"resource_id":"a807b7ab-6cad-4aa6-87d0-e283a7353a0f","fields":[{"type":"int4","id":"_id"},{"type":"text","id":"quarter"},{"type":"numeric","id":"volume_of_mobile_data"}],"records":[{"volume_of_mobile_data":"0.000384","quarter":"2004-Q3","_id":1},{"volume_of_mobile_data":"0.000543","quarter":"2004-Q4","_id":2},{"volume_of_mobile_data":"0.00062","quarter":"2005-Q1","_id":3},{"volume_of_mobile_data":"0.000634","quarter":"2005-Q2","_id":4},{"volume_of_mobile_data":"0.000718","quarter":"2005-Q3","_id":5}],"_links":{"start":"/api/action/datastore_search?limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f","next":"/api/action/datastore_search?offset=5&limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f"},"limit":5,"total":59}
     */

    public String help;
    public boolean success;
    public ResultBean result;

    public static class ResultBean {
        /**
         * resource_id : a807b7ab-6cad-4aa6-87d0-e283a7353a0f
         * fields : [{"type":"int4","id":"_id"},{"type":"text","id":"quarter"},{"type":"numeric","id":"volume_of_mobile_data"}]
         * records : [{"volume_of_mobile_data":"0.000384","quarter":"2004-Q3","_id":1},{"volume_of_mobile_data":"0.000543","quarter":"2004-Q4","_id":2},{"volume_of_mobile_data":"0.00062","quarter":"2005-Q1","_id":3},{"volume_of_mobile_data":"0.000634","quarter":"2005-Q2","_id":4},{"volume_of_mobile_data":"0.000718","quarter":"2005-Q3","_id":5}]
         * _links : {"start":"/api/action/datastore_search?limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f","next":"/api/action/datastore_search?offset=5&limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f"}
         * limit : 5
         * total : 59
         */

        @JSONField(name = "resource_id")
        public String resourceId;
        public int limit;
        public int total;
        public List<FieldsBean> fields;
        public List<RecordsBean> records;
        @JSONField(name = "_links")
        public LinksBean links;

        public static class LinksBean {
            /**
             * start : /api/action/datastore_search?limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f
             * next : /api/action/datastore_search?offset=5&limit=5&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f
             */

            public String start;
            public String next;
        }

        public static class FieldsBean {
            /**
             * type : int4
             * id : _id
             */

            public String type;
            public String id;
        }

        public static class RecordsBean {
            /**
             * volume_of_mobile_data : 0.000384
             * quarter : 2004-Q3
             * _id : 1
             */

            @JSONField(name = "volume_of_mobile_data")
            public String volumeOfMobileData;
            @JSONField(name = "_id")
            public String id;
            public String quarter;

            public Double getVolumeOfMobileData() {
                return Double.parseDouble(volumeOfMobileData);
            }
        }
    }

}
