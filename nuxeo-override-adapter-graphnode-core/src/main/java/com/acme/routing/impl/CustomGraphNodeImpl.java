package com.acme.routing.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.routing.api.exception.DocumentRouteException;
import org.nuxeo.ecm.platform.routing.core.impl.GraphNodeImpl;
import org.nuxeo.ecm.platform.routing.core.impl.GraphRouteImpl;

public class CustomGraphNodeImpl extends GraphNodeImpl {

    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(CustomGraphNodeImpl.class);

    public CustomGraphNodeImpl(DocumentModel doc) {
        super(doc);
        if (log.isDebugEnabled()) {
            log.debug("<ctor> " + doc);
        }
    }

    public CustomGraphNodeImpl(DocumentModel doc, GraphRouteImpl graph) {
        super(doc, graph);
        if (log.isDebugEnabled()) {
            log.debug("<ctor> " + doc + " / " + graph);
        }
    }

    @Override
    public List<String> evaluateTaskAssignees() throws DocumentRouteException {
        if (log.isDebugEnabled()) {
            log.debug("<evaluateTaskAssignees> DO YOUR CUSTOM STUFF HERE");
        }
        // TODO
        return super.evaluateTaskAssignees();
    }

}
