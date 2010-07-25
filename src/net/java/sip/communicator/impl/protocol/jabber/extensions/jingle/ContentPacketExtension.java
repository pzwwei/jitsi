/*
 * SIP Communicator, the OpenSource Java VoIP and Instant Messaging client.
 *
 * Distributable under LGPL license.
 * See terms of license at gnu.org.
 */
package net.java.sip.communicator.impl.protocol.jabber.extensions.jingle;

import net.java.sip.communicator.impl.protocol.jabber.extensions.*;


/**
 * The Jingle "content" element contains the entire description of the session
 * being initiated. Among other things it contains details such as transport,
 * or (in the case of RTP) payload type list.
 *
 * @author Emil Ivov
 */
public class ContentPacketExtension extends AbstractPacketExtension
{
    /**
     * The name of the "content" element.
     */
    public static final String ELEMENT_NAME = "content";

    /**
     * The name of the "creator" argument.
     */
    public static final String CREATOR_ATTR_NAME = "creator";

    /**
     * The name of the "disposition" argument.
     */
    public static final String DISPOSITION_ATTR_NAME = "disposition";

    /**
     * The name of the "name" argument.
     */
    public static final String NAME_ATTR_NAME = "name";

    /**
     * The name of the "senders" argument.
     */
    public static final String SENDERS_ATTR_NAME = "senders";

    /**
     * The values we currently support for the creator field.
     */
    public static enum CreatorEnum
    {
        /**
         * Indicates that content type was originally generated by the session
         * initiator
         */
        initiator,

        /**
         * Indicates that content type was originally generated by the session
         * addressee
         */
        responder
    };

    /**
     * The values we currently support for the <tt>senders</tt> field.
     */
    public static enum SendersEnum
    {
        /**
         * Indicates that only the initiator will be generating content
         */
        initiator,

        /**
         * Indicates that no one in this session will be generating content
         */
        none,

        /**
         * Indicates that only the responder will be generating content
         */
        responder,

        /**
         * Indicates that both parties in this session will be generating
         * content
         */
        both
    };

    /**
     * Creates a new <tt>ContentPacketExtension</tt>.
     */
    public ContentPacketExtension()
    {
        super(null, ELEMENT_NAME);
    }

    /**
     * Creates a new <tt>ContentPacketExtension</tt>. instance with only required
     * parameters.
     *
     * @param creator indicates which party originally generated the content
     * type
     * @param disposition indicates how the content definition is to be
     * interpreted by the recipient
     * @param name the content type according to the creator
     * @param senders the parties in the session will be generating content.
     */
    public ContentPacketExtension(CreatorEnum creator,
                                  String disposition,
                                  String name,
                                  SendersEnum senders)
    {
        super(null, ELEMENT_NAME);
        super.setAttribute(CREATOR_ATTR_NAME, creator);
        super.setAttribute(DISPOSITION_ATTR_NAME, disposition);
        super.setAttribute(NAME_ATTR_NAME, name);
        super.setAttribute(SENDERS_ATTR_NAME, senders);
    }

    /**
     * Creates a new <tt>ContentPacketExtension</tt> instance with the specified
     * parameters.
     *
     * @param creator indicates which party originally generated the content
     * type
     * interpreted by the recipient
     * @param name the content type according to the creator
     */
    public ContentPacketExtension(CreatorEnum creator, String name)
    {
        super(null, ELEMENT_NAME);
        super.setAttribute(CREATOR_ATTR_NAME, creator);
        super.setAttribute(NAME_ATTR_NAME, name);
    }

    /**
     * Returns the value of the creator argument. The creator argument indicates
     * which party originally generated the content type and is used to prevent
     * race conditions regarding modifications; the defined values are
     * "initiator" and "responder" (where the default is "initiator"). The value
     * of the 'creator' attribute for a given content type MUST always match
     * the party that originally generated the content type, even for Jingle
     * actions that are sent by the other party in relation to that content
     * type (e.g., subsequent content-modify or transport-info messages). The
     * combination of the 'creator' attribute and the 'name' attribute is
     * unique among both parties to a Jingle session.
     *
     * @return the value of this content's creator argument.
     */
    public CreatorEnum getCreator()
    {
        return CreatorEnum.valueOf(getAttributeAsString(CREATOR_ATTR_NAME));
    }

    /**
     * Sets the value of the creator argument. The creator argument indicates
     * which party originally generated the content type and is used to prevent
     * race conditions regarding modifications; the defined values are
     * "initiator" and "responder" (where the default is "initiator"). The value
     * of the 'creator' attribute for a given content type MUST always match
     * the party that originally generated the content type, even for Jingle
     * actions that are sent by the other party in relation to that content
     * type (e.g., subsequent content-modify or transport-info messages). The
     * combination of the 'creator' attribute and the 'name' attribute is
     * unique among both parties to a Jingle session.
     *
     * @param creator the value of this content's creator argument.
     */
    public void setCreator(CreatorEnum creator)
    {
        setAttribute(CREATOR_ATTR_NAME, creator);
    }

    /**
     * Returns the value of the disposition argument if it exists or
     * <tt>null</tt> if it doesn't. The disposition argument indicates how the
     * content definition is to be interpreted by the recipient. The meaning of
     * this attribute matches the <tt>"Content-Disposition"</tt> header as
     * defined in RFC 2183 and applied to SIP by RFC 3261. The value of this
     * attribute SHOULD be one of the values registered in the IANA Mail
     * Content Disposition Values and Parameters Registry. The default value of
     * this attribute is "session".
     *
     * @return the value of this content's disposition argument.
     */
    public String getDisposition()
    {
        return getAttributeAsString(DISPOSITION_ATTR_NAME);
    }

    /**
     * Sets the value of the disposition argument if it exists or
     * <tt>null</tt> if it doesn't. The disposition argument indicates how the
     * content definition is to be interpreted by the recipient. The meaning of
     * this attribute matches the <tt>"Content-Disposition"</tt> header as
     * defined in RFC 2183 and applied to SIP by RFC 3261. The value of this
     * attribute SHOULD be one of the values registered in the IANA Mail
     * Content Disposition Values and Parameters Registry. The default value of
     * this attribute is "session".
     *
     * @param disposition the value of this content's disposition argument.
     */
    public void setDisposition(String disposition)
    {
        setAttribute(DISPOSITION_ATTR_NAME, disposition);
    }

    /**
     * Returns the value of the name argument if it exists or
     * <tt>null</tt> if it doesn't. The name argument is a unique identifier
     * for the content type according to the creator, which MAY have meaning to
     * a human user in order to differentiate this content type from other
     * content types (e.g., two content types containing video media could
     * differentiate between "room-pan" and "slides"). If there are two content
     * types with the same value for the 'name' attribute, they shall understood
     * as alternative definitions for the same purpose (e.g., a legacy method
     * and a standards-based method for establishing a voice call), typically
     * to smooth the transition from an older technology to Jingle.
     *
     * @return the value of this content's <tt>name</tt> argument.
     */
    public String getName()
    {
        return getAttributeAsString(NAME_ATTR_NAME);
    }

    /**
     * Sets the value of the name argument if it exists or
     * <tt>null</tt> if it doesn't. The name argument is a unique identifier
     * for the content type according to the creator, which MAY have meaning to
     * a human user in order to differentiate this content type from other
     * content types (e.g., two content types containing video media could
     * differentiate between "room-pan" and "slides"). If there are two content
     * types with the same value for the 'name' attribute, they shall understood
     * as alternative definitions for the same purpose (e.g., a legacy method
     * and a standards-based method for establishing a voice call), typically
     * to smooth the transition from an older technology to Jingle.
     *
     * @param name the value of this content's <tt>name</tt> argument.
     */
    public void setName(String name)
    {
        setAttribute(NAME_ATTR_NAME, name);
    }

    /**
     * Returns the value of the senders argument which indicates the parties
     * that will be generating content in this session; the allowable values
     * are defined in the <tt>SendersEnum</tt>.
     *
     * @return a {@link SendersEnum} instance indicating the the parties that
     * will be generating content in this session.
     */
    public SendersEnum getSenders()
    {
        return SendersEnum.valueOf( getAttributeAsString(SENDERS_ATTR_NAME) );
    }

    /**
     * Sets the value of the senders argument which indicates the parties
     * that will be generating content in this session; the allowable values
     * are defined in the <tt>SendersEnum</tt>.
     *
     * @param senders a {@link SendersEnum} instance indicating the the parties
     * that will be generating content in this session.
     */
    public void setSenders(SendersEnum senders)
    {
        setAttribute(SENDERS_ATTR_NAME, senders.toString());
    }
}
