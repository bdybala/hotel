import React, {Component} from 'react';
import {Panel} from "react-bootstrap";
import ReservationCheckingCustomer from "./ReservationCheckingCustomer";

class ReservationsNewCheckIn extends Component {

  constructor(props) {
    super(props);

    this.handleClick = this.handleClick.bind(this);
    this.handleCheckInClick = this.handleCheckInClick.bind(this);

    this.onFirstNameChange = this.onFirstNameChange.bind(this);
    this.onLastNameChange = this.onLastNameChange.bind(this);
    this.onPeselChange = this.onPeselChange.bind(this);
    this.onIdCardNumberChange = this.onIdCardNumberChange.bind(this);
    this.removeCheckedUser = this.removeCheckedUser.bind(this);

    this.state = {
      checkedUsers: [],
      formValid: false
    }
  }

  handleClick() {
    let oldArray = this.state.checkedUsers;
    oldArray.push({
      firstName: '',
      lastName: '',
      pesel: '',
      idCardNumber: '',
    });
    this.setState({checkedUsers: oldArray}, this.checkForm());
  }

  handleCheckInClick() {
    console.log(this.state.checkedUsers);
    this.props.doCheckIn(this.props.reservation, this.state.checkedUsers);
    this.setState({checkedUsers: []})
    // todo Handle new Visit started:
    // todo  BACKEND: flag reservation as 'checked' and don't show it on FRONT (GET reservations)
    // todo FRONT: hide panel and refresh
    // todo show Visits in other place
  }

  onFirstNameChange(index, firstName) {
    let checkedUsers = [...this.state.checkedUsers];
    let updatedUser = checkedUsers[index];
    updatedUser.firstName = firstName;
    checkedUsers[index] = updatedUser;
    this.setState({checkedUsers}, this.checkForm());
  }

  onLastNameChange(index, lastName) {
    let checkedUsers = [...this.state.checkedUsers];
    let updatedUser = checkedUsers[index];
    updatedUser.lastName = lastName;
    checkedUsers[index] = updatedUser;
    this.setState({checkedUsers}, this.checkForm());
  }

  onPeselChange(index, pesel) {
    let checkedUsers = [...this.state.checkedUsers];
    let updatedUser = checkedUsers[index];
    updatedUser.pesel = pesel;
    checkedUsers[index] = updatedUser;
    this.setState({checkedUsers}, this.checkForm());
  }

  onIdCardNumberChange(index, idCardNumber) {
    let checkedUsers = [...this.state.checkedUsers];
    let updatedUser = checkedUsers[index];
    updatedUser.idCardNumber = idCardNumber;
    checkedUsers[index] = updatedUser;
    this.setState({checkedUsers}, this.checkForm());
  }

  checkForm() {
    if (this.state.checkedUsers.length === 0) {
      this.setState({formValid: false});
    } else {
      var formValid = true;
      this.state.checkedUsers.forEach(user => {
        if (user.firstName.trim() === '' || user.lastName.trim() === ''
            || user.pesel.trim() === '' || user.idCardNumber.trim() === '') {
          formValid = false;
        }
      });
      this.setState({formValid: formValid});
    }
  }

  removeCheckedUser(index) {
    let checkedUsers = [...this.state.checkedUsers];
    checkedUsers.splice(index, 1);
    this.setState({checkedUsers: checkedUsers}, () => {
      this.checkForm();
    });
  }

  render() {
    if (!this.props.show) {
      return null;
    }
    let listOfCheckedUsers = this.state.checkedUsers.map((item, i) => {
      return <ReservationCheckingCustomer
          key={i}
          index={i}
          user={item}
          onFirstNameChange={this.onFirstNameChange}
          onLastNameChange={this.onLastNameChange}
          onPeselChange={this.onPeselChange}
          onIdCardNumberChange={this.onIdCardNumberChange}
          removeCheckedUser={this.removeCheckedUser}
      > </ReservationCheckingCustomer>
    });

    return (
        <div className='UserRegisterPanel'>
          <Panel>
            <Panel.Heading>
              <Panel.Title className="panelTitle">
                Meldowanie do rezerwacji
                dla <b>{this.props.reservation.email}</b> z <b>{new Date(
                  this.props.reservation.since).toDateString()}</b>
              </Panel.Title>
            </Panel.Heading>
            <Panel.Body>
              {listOfCheckedUsers}
              <button type="button" className="btn btn-primary"
                      onClick={this.handleClick}
              >
                Dodaj kolejną osobę
              </button>
              <button type="button" className="btn btn-success"
                      onClick={this.handleCheckInClick}
                      disabled={!this.state.formValid}
              >
                Melduj
              </button>
            </Panel.Body>
          </Panel>
        </div>)

  }
}

export default ReservationsNewCheckIn;