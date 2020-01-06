import React from 'react';
import { Route, Switch } from 'react-router-dom';

import Home from '../../hotel-front/src/containers/Home';
import Availability from './containers/Availability';
import Rooms from './containers/Rooms';
import ManageUsers from '../../hotel-front/src/containers/management/ManageUsers';
import ManageRooms from '../../hotel-front/src/containers/management/ManageRooms';
import ManageReservations from '../../hotel-front/src/containers/management/ManageReservations';
import About from './containers/About';

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