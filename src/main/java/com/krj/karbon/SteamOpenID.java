/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krj.karbon;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.consumer.ConsumerManager;
import org.openid4java.consumer.VerificationResult;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.discovery.DiscoveryInformation;
import org.openid4java.discovery.Identifier;
import org.openid4java.message.AuthRequest;
import org.openid4java.message.MessageException;
import org.openid4java.message.ParameterList;

/**
 *
 * @author jolley
 */
public class SteamOpenID {

    private static final String STEAM_OPENID = "http://steamcommunity.com/openid";
    private final ConsumerManager manager;
    private final Pattern STEAM_REGEX = Pattern.compile("(\\d+)");
    private DiscoveryInformation discovered;

    public SteamOpenID() {
        manager = new ConsumerManager();
        manager.setMaxAssocAttempts(0);
        try {
            discovered = manager.associate(manager.discover(STEAM_OPENID));
        } catch (DiscoveryException ex) {
            Logger.getLogger(SteamOpenID.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String login(String callbackURL) {
        if (this.discovered == null) {
            return null;
        }
        try {
            AuthRequest authReq = manager.authenticate(this.discovered, callbackURL);
            return authReq.getDestinationUrl(true);
        } catch (MessageException | ConsumerException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String verify(String recievingURL, Map responseMap) {
        try {
            if (this.discovered == null) {
                return null;
            }
            ParameterList responseList = new ParameterList(responseMap);
            
            VerificationResult verification = manager.verify(recievingURL, responseList, this.discovered);
            Identifier verifiedId = verification.getVerifiedId();
            if (verifiedId != null) {
                String id = verifiedId.getIdentifier();
                Matcher matcher = STEAM_REGEX.matcher(id);
                if (matcher.find()) {
                    System.out.println("The steamId is: " + matcher.group(1));
                    return matcher.group(1);
                }
            }
        } catch (MessageException | DiscoveryException | AssociationException ex) {
            Logger.getLogger(SteamOpenID.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
