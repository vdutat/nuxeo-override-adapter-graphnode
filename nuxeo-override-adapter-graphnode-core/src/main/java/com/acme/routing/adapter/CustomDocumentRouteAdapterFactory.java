package com.acme.routing.adapter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.platform.routing.api.DocumentRoutingConstants;
import org.nuxeo.ecm.platform.routing.api.DocumentRoutingConstants.ExecutionTypeValues;
import org.nuxeo.ecm.platform.routing.core.adapter.DocumentRouteAdapterFactory;

import com.acme.routing.impl.CustomGraphNodeImpl;
import com.acme.routing.impl.CustomGraphRouteImpl;

public class CustomDocumentRouteAdapterFactory extends DocumentRouteAdapterFactory {

    private static final Log log = LogFactory.getLog(CustomDocumentRouteAdapterFactory.class);

    @Override
    public Object getAdapter(DocumentModel doc, @SuppressWarnings("rawtypes") Class itf) {
        if (log.isDebugEnabled()) {
            log.debug("<getAdapter> " + doc + " / " + itf);
        }
        String type = doc.getType();
        if (doc.hasFacet(DocumentRoutingConstants.DOCUMENT_ROUTE_DOCUMENT_FACET)) {
            ExecutionTypeValues executionType = getExecutionType(doc, type);
            switch (executionType) {
            case graph:
                return new CustomGraphRouteImpl(doc);
            default:
                return super.getAdapter(doc, itf);
            }
        } else if ("RouteNode".equals(type)) {
            return new CustomGraphNodeImpl(doc);
        } else {
            return super.getAdapter(doc, itf);
        }
    }

}
