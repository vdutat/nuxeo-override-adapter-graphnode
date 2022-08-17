package com.acme.routing.impl;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.nuxeo.ecm.core.api.CoreSession;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentModelList;
import org.nuxeo.ecm.platform.routing.api.exception.DocumentRouteException;
import org.nuxeo.ecm.platform.routing.core.impl.GraphNode;
import org.nuxeo.ecm.platform.routing.core.impl.GraphRouteImpl;

public class CustomGraphRouteImpl extends GraphRouteImpl {
    
    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(CustomGraphRouteImpl.class);

    public CustomGraphRouteImpl(DocumentModel doc) {
        super(doc);
        if (log.isDebugEnabled()) {
            log.debug("<ctor> " + doc);
        }
    }

    @Override
    protected String computeNodes() {
        if (log.isDebugEnabled()) {
            log.debug("<computeNodes> ");
        }
        CoreSession session = document.getCoreSession();
        DocumentModelList children = session.getChildren(document.getRef());
        nodes = new ArrayList<>(children.size());
        nodesById = new HashMap<>();
        String startNodeId = null;
        for (DocumentModel doc : children) {
            if ("RouteNode".equals(doc.getType())) {
                GraphNode node = doc.getAdapter(GraphNode.class);
                String id = node.getId();
                if (nodesById.put(id, node) != null) {
                    throw new DocumentRouteException("Duplicate nodes with id: " + id);
                }
                nodes.add(node);
                if (node.isStart()) {
                    if (startNodeId != null) {
                        throw new DocumentRouteException("Duplicate start nodes: " + startNodeId + " and " + id);
                    }
                    startNodeId = id;
                }
            }
        }
        return startNodeId;
    }

}
