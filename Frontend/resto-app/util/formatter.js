export function capitalize(string) {
    // Check if the input string is empty or null
    if (!string) return '';
  
    // Convert the first character to uppercase and concatenate it with the rest of the string
    return string.charAt(0).toUpperCase() + string.slice(1);
  }
  
  // Example usage:
  export function formatTimestamp(timestamp) {
    const options = {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: 'numeric',
      minute: 'numeric',
    };
  
    const date = new Date(timestamp);
    const formattedDate = date.toLocaleDateString('en-US', options);
    const amOrPm = date.getHours() >= 12 ? 'pm' : 'am';
    const formattedTime = date.toLocaleTimeString('en-US', { hour: 'numeric', minute: 'numeric' });
  
    return `${formattedDate}`;
  }
  
 