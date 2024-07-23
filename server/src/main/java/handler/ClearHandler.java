package handler;

import reqres.ClearRequest;
import reqres.ClearResult;
import spark.*;
import service.*;

public class ClearHandler extends Handler{
    public Object handleRequest(Request req, Response res) throws ErrorException {
        ClearRequest request = new ClearRequest();
        ClearResult result = clearService.clear(request);
        return toJson(result);
    }
}
