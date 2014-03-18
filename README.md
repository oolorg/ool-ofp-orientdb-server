ool-ofp-orientdb-server
=======================
The ool-ofp-orientdb-server will manage of Physical wiring,
and calculate patch link information for set in open flow by using the Dijkstra algorithm .

Requirements
-----------------------
* OrientDB v1.3
* Apache2
* Tomcat7


Install
-----------------------
 Maybe later...

Definitions
-----------------------
This terms and definitions below.

### Device ###
### Port ###
### Logical Topology ###
### Physical Topology ###

Design
-----------------------

Module Architecture:

        +-------------------------------------------------------+
        |                    ool-ofp-manager                    |
        +-------+---------------------------------+-------------+
                |                                 |
                |                                 |
                |                   +-------------+-------------+
                |                   |  ool-ofp-orientdb-server  |
                |                   +---------------------------+
        +-------+-------+
        | ool-ofp-agent |
        +---------------+
                |
        +-------+-------+
        |    ool-ofc    |
        +-------+-------+
                |
          +-----+-----+
          | OFP(OFS)  |
          +-----------+

REST API
-----------------------
### Create Node and Topology ###
<table>
  <tr>
    <th>Field name</th>
    <th>Type</th>
    <th>Description</th>
    <th>Requirement</th>
  </tr>
  <tr>
    <td>deviceName</td>
    <td>JSON String</td>
    <td>A unique device name.</td>
    <td>Mandatory</td>
  </tr>
  <tr>
    <td>type</td>
    <td>JSON String</td>
    <td>'Server' or 'Switch'.</td>
    <td>Mandatory</td>
  </tr>
  <tr>
    <td>ofpFlag</td>
    <td>JSON Boolean</td>
    <td>ofp configurations switch.</td>
    <td>Mandatory</td>
  </tr>
</table>

  Example: POST /deviceManager/nodeCreate

    {
          'deviceName':'server1',
          'type':'Server',
          'ofpFlag':false
    }

 Maybe later...


Notes
-----------------------

* Dijkstra
 - http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm