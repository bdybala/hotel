import React from 'react';
import { Route, Switch } from 'react-router-dom';

import Home from './containers/Home';
import Availability from './Availability';
import Rooms from './Rooms';
import ManageUsers from './containers/management/ManageUsers';
import ManageRooms from './ManageRooms';
import ManageReservations from './ManageReservations';
import About from './About';

export default () =>
	<Switch>
		<Route exact path="/" component={Home} />
		<Route exact path="/availability" component={Availability} />
		<Route exact path="/rooms" component={Rooms} />
	    <Route exact path="/manage/users" component={ManageUsers} />
 	   	<Route exact path="/manage/rooms" component={ManageRooms} />
    	<Route exact path="/manage/reservations" component={ManageReservations} />
		<Route path="/about" component={About} />
	</Switch>