package me.chrisanabo.security.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.Criteria;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.Filter;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class JsonPathUtils {

    private final static ObjectMapper objectMapper = new ObjectMapper();


    public static final String ADMI_007 = "$['payload']['BusMsg']['Document']['RctAck']['Rpt']['RltdRef']['Ref']";

    public static final String CAMTI_050 = "$['payload']['BusMsg']['AppHdr']['BizMsgIdr']";

    public static final String CAMTI_025 = "$['payload']['BusMsg']['Document']['Rct']['RctDtls']['OrgnlMsgId']['MsgId']";

    public static final String MSG_ID = "$['payload']['BusMsg']['Document']['Rct']['RctDtls']['OrgnlMsgId']['MsgId']";


    public static String getValue(String json, String path){
        DocumentContext jsonContext = JsonPath.parse(json);
        return jsonContext.read(path);
    }

    public static String setValue(String json, String path, String value){
       return JsonPath.parse(json).set(path, value).jsonString();
    }

    public static <T> T deserialize(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (Exception exception) {
    //        log.error("Error deserializing json to {}", tClass.getName(), exception);
        }
        return null;
    }

    public static String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception exception) {
          //  log.error("Error serializing object");
            return null;
        }
    }

    public static void main(String[] args) {
        String admin007 = "{  \"issuer\": \"CHASSGSGXXX\",  \"issueTime\": \"2022-08-29T03:37:40.638Z\", \"payload\": {    \"BusMsg\": {      \"AppHdr\": {        \"xmlns\": \"head.001.001.01\",      \"Fr\": {          \"FIId\": {            \"FinInstnId\": {              \"BICFI\": \"CHASSGSGXXX\"            }          }        },    \"To\": {          \"FIId\": {            \"FinInstnId\": {              \"BICFI\": \"CHASSGSGPTR\"            }          }        },    \"BizMsgIdr\": \"PTESTBICXXXX9202208290337400000001\",        \"MsgDefIdr\": \"camt.003.001.07\",     \"CreDt\": \"2021-02-10T12:46:41.000Z\"      },      \"Document\": {        \"GetAcct\": {       \"MsgHdr\": {            \"MsgId\": \"PTESTBICXXXX9202208290337400000001\",            \"CreDtTm\": \"2021-02-10T19:46:41.000Z\"          },          \"AcctQryDef\": {            \"AcctCrit\": {              \"NewCrit\": {                \"SchCrit\": [                  {                    \"AcctId\": [                      {                        \"EQ\": {                          \"Othr\": {                            \"Id\": \"0xe5faFa951361f7452593ea85Fa40B3D8A615A653\"                          }                        }                      }                    ],                    \"Ccy\": [                      \"USD\"                    ],                    \"Bal\": [                      {                        \"ValDt\": {                          \"Dt\": {                            \"EQDt\": \"2021-07-16\"                          }                        }                      }                    ],                    \"AcctOwnr\": {                      \"Id\": {                        \"OrgId\": {                          \"AnyBIC\": \"CHASSGSGXXX\"                        }                      }                    },                    \"AcctSvcr\": {                      \"FinInstnId\": {                        \"BICFI\": \"CHASUS33MCY\"                      }                    }                  }                ]              }            }          }}}}}}";

        /* working */
        String path007 = "[?(@.issuer == 'CHASSGSGXXX' && @.issueTime == '2022-08-29T03:37:40.638Z')]";

        /* working */
        String anotherPath007 = "[?(@.issuer == 'CHASSGSGXXX' && @.payload.BusMsg.AppHdr.Fr.FIId.FinInstnId.BICFI == 'CHASSGSGXXX')]";


        DocumentContext jsonContext = JsonPath.parse(admin007);
        JSONArray obj = jsonContext.read(anotherPath007);

        System.out.println(obj.toJSONString());


       String json = obj.toJSONString();
       System.out.println(JsonPathUtils.getValue(json,ADMI_007));

//        System.out.println(JsonPathUtils.setValue(admin007, path007, "chris") );
//        System.out.println(JsonPathUtils.getValue(JsonPathUtils.setValue(admin007, path007, "chris"),path007));

        Filter filter = Filter.filter(
                Criteria.where("issuer")
                        .is("CHASSGSGXXX")
                        .and("number")
                        .eq("2022-08-29T03:37:40.638Z")
        );



    }

}
