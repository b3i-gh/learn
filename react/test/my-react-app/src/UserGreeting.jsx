import PropTypes from 'prop-types'

function UserGreeting(props){
    if(props.isLoggedIn){
        return <h2>Welcome {props.username}</h2>
    } else {
        return <h2>Please login</h2>
    }
}

UserGreeting.proptypes = {
    isLoggegIn: PropTypes.bool,
    username: PropTypes.string,
}

export default UserGreeting