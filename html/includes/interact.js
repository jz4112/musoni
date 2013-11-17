/* hack to detect if we're inside android
   if we are, do not load our dummy object, the real thing is already present!
   */

var ua = navigator.userAgent.toLowerCase();
if(ua != null && ua.indexOf("android") == -1)
{
	window.Interact = Object();
	window.Interact.canAnimate = function() { return false; };
	window.Interact.attemptLogIn = function(user, pass) { return prompt("Enter return value for attemptLogIn(" + user + ", " + pass + "):", ""); };
	window.Interact.navigateToMain = function() { alert("navigateToMain called."); };
	
	// main
	window.Interact.navigateToRegistration = function() { alert("navigateToRegistration called."); };
	window.Interact.navigateToLoan = function() { alert("navigateToLoan called."); };
	window.Interact.navigateToBusiness = function() { alert("navigateToBusiness called."); };
	window.Interact.isSyncPending = function() { return confirm("Return true on SyncPending?"); };
	window.Interact.getCurrentUsername = function() { return prompt("Return value for getCurrentUsername", "testuser"); };

	window.Interact.submitClientRegistration = function(keys, values) { alert("submitClientRegistration(" + keys + ", " + values + ") called"); };
}

