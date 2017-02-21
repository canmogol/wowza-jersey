package com.fererlab.wowzajersey.app.restful;

import com.fererlab.wowzajersey.app.WowzaJettyApp;
import com.fererlab.wowzajersey.app.restful.dto.DisconnectResponseDTO;
import com.fererlab.wowzajersey.app.restful.dto.StatusEnum;
import com.fererlab.wowzajersey.core.controller.ClientController;
import com.fererlab.wowzajersey.core.restful.Resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/client")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class ClientResource implements Resource {

    private ClientController clientController = WowzaJettyApp.getInstance().getController(ClientController.class);

    @GET
    @Path("/disconnect")
    public DisconnectResponseDTO disconnectClient(@QueryParam("uniqueId") String uniqueId) {
        DisconnectResponseDTO responseDTO = new DisconnectResponseDTO();
        if (clientController.disconnectClient(uniqueId)) {
            responseDTO.setStatus(StatusEnum.FAIL_OPERATIONAL);
            responseDTO.setMessage("could not disconnect client: " + uniqueId);
        }
        return responseDTO;
    }

}
