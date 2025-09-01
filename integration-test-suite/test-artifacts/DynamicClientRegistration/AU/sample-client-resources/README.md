#Using Sample Resources

Following configs can be used in test-config.xml to use sample SSA and keystores for DCR tests.

    <DCR>
        <SSAPath>Path.To.Directory/ssa.txt</SSAPath>
        <!-- SSA SoftwareId -->
        <SoftwareId>jFQuQ4eQbNCMSqdCog21nF</SoftwareId>
        <!-- SSA Redirect Uri -->
        <RedirectUri>https://www.google.com/redirects/redirect1</RedirectUri>
    </DCR>

Use signing.jks in 'signing-keystore' directory as the Application Keystore, and transport.jks in 'transport-keystore'
directory as the Transport Keystore.

Sample Keystore information:

SSA SoftwareId - jFQuQ4eQbNCMSqdCog21nF
SSA Redirect Uri - https://www.google.com/redirects/redirect1

- Signing Kid = 5dBuoFxTMIrwWd9hzUMVgF2jMbk

- Signing keystore alias = signing

- Signing keystore password = wso2carbon

- Transport Kid - Cxhf6SoaTPr_YB_zbfP3NEdYZzg

- Transport keystore alias = transport

- Transport keystore password = wso2carbon